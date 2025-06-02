package selfbalacingbsts.Trees;

public interface SelfBalancingTrees {
    public boolean insert(String word);

    public boolean delete(String word);

    public void batchInsert(String path);

    public void batchDelete(String path);

    public boolean search(String word);

    public void displayTree();

    public int getHeight();

    public int getSize();
}
