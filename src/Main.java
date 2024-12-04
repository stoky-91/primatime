import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        // 1,
        // 2 -> 3, 4
        // 5 -> 6 -> 7
        List<Node> list = new ArrayList<>();
        list.add(node(1));
        list.add(node(2, new Node[]{ node(3), node(4) }));
        list.add(node(5, new Node[]{ node(6, new Node[]{ node(7) })}));

        // should return 4
        // because (1 + 2 + 3 + 4 + 5 + 6 + 7) / 7 = 4
        System.out.println(getMeanValue(list));

        // Weighted mean value: 3.62
        System.out.println(getWeightedMeanValue(list));
    }

    public static interface Node {
        public double getValue();
        public List<Node> getNodes();
    }

    public static double getMeanValue(List<Node> nodes) {
        return calculateMeanValue(nodes, 1.0);
    }

    public static double getWeightedMeanValue(List<Node> nodes) {
        return calculateMeanValue(nodes, 0.90);
    }

    private static double calculateMeanValue(List<Node> nodes, double decay) {
        SumAndCount result = calculateSumAndCount(nodes, 1.0, decay);
        return result.sum / result.count;
    }

    private static SumAndCount calculateSumAndCount(List<Node> nodes, double weight, double decay) {
        SumAndCount result = new SumAndCount(0, 0);

        for (Node node : nodes) {
            result.add(node.getValue() * weight);
            result.add(calculateSumAndCount(node.getNodes(), weight * decay, decay));
        }

        return result;
    }

    static class SumAndCount {
        double sum;
        double count;

        SumAndCount(double sum, double count) {
            this.sum = sum;
            this.count = count;
        }

        void add(double value) {
            this.sum += value;
            this.count += 1;
        }

        void add(SumAndCount other) {
            this.sum += other.sum;
            this.count += other.count;
        }
    }

    // Builders
    public static Node node(double value) {
        return node(value, new Node[]{});
    }

    public static Node node(double value, Node[] nodes) {
        return new Node() {
            public double getValue() {
                return value;
            }
            public List<Node> getNodes() {
                return Arrays.asList(nodes);
            }
        };
    }
}