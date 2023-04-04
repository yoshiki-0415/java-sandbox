package sandbox;

import sandbox.color.Blue;
import sandbox.color.Color;
import sandbox.color.Red;
import sandbox.color.Yellow;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // Java 16:Records Classes
        SampleRecord record = new SampleRecord(1, "A");

        // Java 15:Text Blocks
        String out = """
            %d
            %s
        """.formatted(record.id(), record.name());
        System.out.println(out);

        System.out.println(getSwitchedString("A"));
        System.out.println(getSwitchedString("B"));
        System.out.println(getSwitchedString("C"));

        System.out.println(getColorName(new Blue()));

        System.out.println(formatterPatternSwitch(100));
        System.out.println(formatterPatternSwitch("A"));
        System.out.println(formatterPatternSwitch(LocalDateTime.now()));

        // Java16:Stream#mapMulti
        List<String> listByMapMulti = Stream.of("abc", "xyz").mapMulti((String str, Consumer<String> consumer) -> {
            consumer.accept(str + "01");
            consumer.accept(str + "02");
            consumer.accept(str + "03");
        }).toList();
        for(String s : listByMapMulti) {
            System.out.println(s);
        }

        SampleService service = new SampleServiceImpl();
        service.defaultMethod();

        // Java 16:Stream#toList
        Stream.of("a", "b").toList();

        // Java 9:Stream#takeWhile
        int[] resultsA = IntStream.iterate(2, x -> x * 2)
                .takeWhile(x -> x <= 100)
                .toArray();
        for (int r : resultsA) {
            System.out.println(r);
        }

        // Java 9:Stream#dropWhile
        int[] resultsB = IntStream.iterate(2, x -> x * 2)
                .dropWhile(x -> x <= 100)
                .takeWhile(x -> x <= 1000)
                .toArray();
        for (int r : resultsB) {
            System.out.println(r);
        }
    }

    private static String getSwitchedString(String str) {
        // Java 14:Switch Expressions
        return switch (str) {
            case "A", "a" -> "Aです。";
            case "B" -> "Bです。";
            default -> {
                System.out.println("不明のブロック");
                yield "不明です。";
            }
        };
    }

    static String getColorName(Color c) {
        // Java16:Pattern Matching
        if (c instanceof Blue blue) {
            return blue.getBlueLabel();
        } else if(c instanceof Red) {
            return "赤";
        } else if(c instanceof Yellow) {
            return "黄";
        }
        return "該当なし";
    }


    static String formatterPatternSwitch(Object o) {
        // Java17:switchのパターンマッチング（プレビュー版）
        return switch (o) {
            case null      -> "*null*";
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default        -> o.toString();
        };
    }

}
