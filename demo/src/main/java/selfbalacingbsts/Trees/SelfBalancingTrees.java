package selfbalacingbsts.Trees;

public interface SelfBalancingTrees {
    public void insert(String word);

    public void delete(String word);

    public void batchInsert(String path);

    public void batchDelete(String path);

    public boolean search(String word);

    public void displayTree();

    public int getHeight();
}
