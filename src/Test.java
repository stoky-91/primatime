import java.util.List;
import java.util.ArrayList;

class Test {
    public static void main(String[] args) {
        Test test = new Test();

        try {
            test.testSingleNodeTree();
            test.testFlatTree();
            test.testZeroTree();
            System.out.println("All tests passed!");
        } catch (AssertionError e) {
            System.err.println(e.getMessage());
        }
    }

    public void testSingleNodeTree() {
        List<Main.Node> singleNodeTree = List.of(node(10));

        assert Main.getMeanValue(singleNodeTree) == 10.0 : "Test SingleNodeTree Failed: Mean";
        assert Math.abs(Main.getWeightedMeanValue(singleNodeTree) - 10.0) < 1e-9 : "Test SingleNodeTree Failed: Weighted Mean";
    }

    public void testFlatTree() {
        List<Main.Node> flatTree = List.of(node(1), node(2), node(3));

        assert Main.getMeanValue(flatTree) == 2.0 : "Test FlatTree Failed: Mean";
        assert Math.abs(Main.getWeightedMeanValue(flatTree) - 2.0) < 1e-9 : "Test FlatTree Failed: Weighted Mean";
    }

    public void testZeroTree() {
        List<Main.Node> zeroTree = List.of(node(0), node(0, new Main.Node[]{ node(0) }));

        assert Main.getMeanValue(zeroTree) == 0.0 : "Test ZeroTree Failed: Mean";
        assert Math.abs(Main.getWeightedMeanValue(zeroTree) - 0.0) < 1e-9 : "Test ZeroTree Failed: Weighted Mean";
    }

    public static Main.Node node(double value) {
        return Main.node(value);
    }

    public static Main.Node node(double value, Main.Node[] nodes) {
        return Main.node(value, nodes);
    }
}
