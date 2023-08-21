import java.util.*;


public class Main {

    private static void LoadSiteStates(Hashtable<String, String> exState, Hashtable<String, String> curState){

        exState.put("https://example.com/article2", "Lorem ipsum");
        exState.put("https://example.com/arcicle23", "Greater than ever");
        exState.put("https://example.com/ToS", "1.1. Common principles");
        exState.put("https://example.com/TTM", "30 days");
        exState.put("https://example.com/ApiToS", "0.0. API functionality");

        curState.put("https://example.com/article2", "Lorem ipsum!");
        curState.put("https://example.com/arcicle23", "Greater than ever");
        curState.put("https://example.com/ToS", "1.1. Common principles, 1.2. Uncommon principles");
        curState.put("https://example.com/TTM", "14 days");
        curState.put("https://example.com/CommonSense", "Pluto is a planet");

    }

    public static void main(String[] args) {

        Hashtable<String, String> ex_state = new Hashtable<>();
        Hashtable<String, String> cur_state = new Hashtable<>();

        LoadSiteStates(ex_state, cur_state);

        SiteStateDelta delta = DetectDelta(ex_state, cur_state);

        String report = GenerateStandardReport(delta.getDeleted(), delta.getAdded(), delta.getChanged());

        System.out.println(report);
    }


    private static SiteStateDelta DetectDelta(Hashtable<String, String> prevState, Hashtable<String, String> curState){

        HashSet<String> prevKeys = new HashSet<>(prevState.keySet());
        HashSet<String> curKeys = new HashSet<>(curState.keySet());

        HashSet<String> union = new HashSet<>(prevState.keySet());
        union.addAll(curState.keySet());

        HashSet<String> deleted = new HashSet<>();
        HashSet<String> added = new HashSet<>();
        HashSet<String> changed = new HashSet<>();

        for (String s : union){
            if (!prevKeys.contains(s))
                added.add(s);//check it
            else if (!curKeys.contains(s)){
                deleted.add(s);//check it
            } else if (! (curState.get(s).equals(prevState.get(s)))){
                changed.add(s);
            }
        }

        return new SiteStateDelta(added, deleted, changed);
    }

    private static String GenerateStandardReport(Set<String> deleted, Set<String> added, Set<String> changed){
        return  "Здравствуйте, дорогая и.о. секретаря\n\n" +
                "За последние сутки во вверенных вам сайтах произошли следующие изменения:\n\n"+
                "Исчезли следующие страницы:\n\t" +
                String.join("\n\t", deleted) + "\n" +
                "Появились следующие новые страницы:\n\t" +
                String.join("\n\t", added) + "\n" +
                "Изменились следующие страницы:\n\t" +
                String.join("\n\t", changed) + "\n\n"+
                "С уважением,\n"+
                "автоматизированная система мониторинга.";
    }
}