

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;


/**
 *
 * @author jldeleage
 */
public class Main {
    
    public static void main(String[] args) {
        List<String> maListe = Arrays.asList("1", "deux", "3", "4", "cinq");
        BinaryOperator<Optional<Integer>> accu = Main::accu;
        Optional<Integer> reduce = maListe.stream()
                .map(Main::mapper)
                .reduce(Optional.empty(), accu);
        System.out.println(reduce);
        maListe = Arrays.asList("un", "deux", "trois", "quatre", "cinq");
        reduce = maListe.stream()
                .map(Main::mapper)
                .reduce(Optional.empty(), accu);
        System.out.println(reduce);
    }


    public static Optional<Integer> accu(Optional<Integer> x, Optional<Integer> y) {
        if (x.isPresent()) {
            if (y.isPresent())
                return Optional.of(x.get() + y.get());
            return x;
        }
        return y;
    }

    public static Optional<Integer>    mapper(String s) {
        try {
            int parseInt = Integer.parseInt(s);
            return Optional.of(parseInt);
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }


}
