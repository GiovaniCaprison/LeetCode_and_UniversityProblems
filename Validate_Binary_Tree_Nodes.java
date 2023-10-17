class Validate_Binary_Tree_Nodes {
    // This array will be used to represent the parent of each node.
    private int[] parent;

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        // Initialize parent array so that each node is its own parent (standard union-find initialization)
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Check edges between each node and its children.
        int edges = 0;
        for (int i = 0; i < n; i++) {
            // If there's an issue with the left or right child, return false.
            if (!processEdge(i, leftChild[i])) return false;
            if (!processEdge(i, rightChild[i])) return false;
        }

        // A valid tree with 'n' nodes should have exactly 'n-1' edges.
        if (edges != n - 1) return false;

        // Count how many nodes act as their own parent, i.e., are roots of their trees.
        int rootCount = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) rootCount++;
        }

        // A valid tree should have exactly one root.
        return rootCount == 1;
    }

    private boolean processEdge(int node, int child) {
        // If the child does not exist (represented by -1), there's nothing to process.
        if (child == -1) return true;

        // If the child already has a different parent, it's an invalid tree.
        if (parent[child] != child) return false;

        // Set the parent of the child to be the current node.
        parent[child] = node;

        // Check for cycles: If the node and its child belong to the same set, we have a cycle.
        if (find(node) == find(child)) return false;

        // Merge the sets of the node and its child.
        union(node, child);

        return true;
    }

    // 'find' function for union-find: It returns the root of the tree the node belongs to.
    private int find(int x) {
        // If the node is not its own parent, recursively find its root.
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 'union' function for union-find: Merges the sets of x and y.
    private void union(int x, int y) {
        int rootX = find(x);  // Find root of x
        int rootY = find(y);  // Find root of y

        // If x and y are in different sets, make one of them the parent of the other.
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}

