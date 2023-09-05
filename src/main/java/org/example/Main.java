package org.example;

import java.util.*;

public class Main {
    private static int[] parent;
    private static int[] size;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        parent = new int[n + 1];  // Массив для хранения родителя каждого духа
        size = new int[n + 1];  // Массив для хранения размера каждой банды
        for (int i = 1; i <= n; i++) {
            parent[i] = i;  // Изначально каждый дух является родителем самого себя
            size[i] = 1;  // Изначально размер каждой банды равен 1
        }

        for (int i = 0; i < m; i++) {
            int type = scanner.nextInt();
            int x = scanner.nextInt();
            int y = 0;
            if (type != 3) {
                y = scanner.nextInt();
            }

            if (type == 1) {
                union(x, y);  // Объединение банд духов x и y
            } else if (type == 2) {
                if (find(x) == find(y)) {
                    System.out.println("YES");  // Духи x и y находятся в одной банде
                } else {
                    System.out.println("NO");  // Духи x и y находятся в разных бандах
                }
            } else if (type == 3) {
                System.out.println(size[find(x)]);  // Вывод размера банды, в которой находится дух x
            }
        }
    }

    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Рекурсивно ищем родителя духа x и сохраняем его
        }
        return parent[x];
    }

    private static void union(int x, int y) {
        int rootX = find(x);  // Находим корневую банду духа x
        int rootY = find(y);  // Находим корневую банду духа y
        if (rootX != rootY) {  // Если корневые банды различны, выполняем объединение
            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;  // Дух x становится членом банды y
                size[rootY] += size[rootX];  // Обновляем размер банды y
            } else {
                parent[rootY] = rootX;  // Дух y становится членом банды x
                size[rootX] += size[rootY];  // Обновляем размер банды x
            }
        }
    }
}
