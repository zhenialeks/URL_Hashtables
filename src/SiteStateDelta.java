import java.util.HashSet;

class SiteStateDelta {
    private HashSet<String> added;
    private HashSet<String> deleted;
    private HashSet<String> changed;

    public SiteStateDelta(HashSet<String> a, HashSet<String> d, HashSet<String> c) {
        added = a;
        deleted = d;
        changed = c;
    }

    public HashSet<String> getAdded() {
        return added;
    }

    public void setAdded(HashSet<String> added) {
        this.added = added;
    }

    public HashSet<String> getDeleted() {
        return deleted;
    }

    public void setDeleted(HashSet<String> deleted) {
        this.deleted = deleted;
    }

    public HashSet<String> getChanged() {
        return changed;
    }

    public void setChanged(HashSet<String> changed) {
        this.changed = changed;
    }
}
