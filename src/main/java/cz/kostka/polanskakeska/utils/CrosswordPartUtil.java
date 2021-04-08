package cz.kostka.polanskakeska.utils;

import cz.kostka.polanskakeska.entity.CrosswordPart;

import java.util.Map;

public class CrosswordPartUtil {
    public static final Map<Integer, PartInfoHolder> CROSSWORD_PARTS_TEMPLATE = Map.ofEntries(
            Map.entry(1, new PartInfoHolder(1, 1, 1, 3)),
            Map.entry(2, new PartInfoHolder(2, 1, 2, 2)),
            Map.entry(3, new PartInfoHolder(3, 2, 1, 3)),
            Map.entry(4, new PartInfoHolder(4, 2, 2, 4)),
            Map.entry(5, new PartInfoHolder(5, 3, 1, 2)),
            Map.entry(6, new PartInfoHolder(6, 3, 2, 3)),
            Map.entry(7, new PartInfoHolder(7, 4, 1, 3)),
            Map.entry(8, new PartInfoHolder(8, 4, 2, 2)),
            Map.entry(9, new PartInfoHolder(9, 4, 3, 3)),
            Map.entry(10, new PartInfoHolder(10, 4, 4, 4)),
            Map.entry(11, new PartInfoHolder(11, 5, 1, 2)),
            Map.entry(12, new PartInfoHolder(12, 5, 2, 2)),
            Map.entry(13, new PartInfoHolder(13, 5, 3, 2)),
            Map.entry(14, new PartInfoHolder(14, 6, 1, 2)),
            Map.entry(15, new PartInfoHolder(15, 6, 2, 2)),
            Map.entry(16, new PartInfoHolder(16, 7, 1, 3)),
            Map.entry(17, new PartInfoHolder(17, 7, 2, 3)),
            Map.entry(18, new PartInfoHolder(18, 8, 1, 2)),
            Map.entry(19, new PartInfoHolder(19, 8, 2, 4)),
            Map.entry(20, new PartInfoHolder(20, 8, 3, 4)));

    public static CrosswordPart getEmptyPartForCache(final int cacheNumber) {
        final PartInfoHolder partInfoHolder = CROSSWORD_PARTS_TEMPLATE.get(cacheNumber);
        final int letterCount = partInfoHolder.getLetterCount();
        return new CrosswordPart(partInfoHolder.getCacheNumber(), partInfoHolder.getWord(), partInfoHolder.getPosition(),
                letterCount, getSpaces(letterCount));
    }

    private static String getSpaces(final int count) {
        return "_".repeat(Math.max(0, count));
    }

    static class PartInfoHolder {
        private int cacheNumber;
        private int word;
        private int position;
        private int letterCount;

        public PartInfoHolder(final int cacheNumber, final int word, final int position, final int letterCount) {
            this.cacheNumber = cacheNumber;
            this.word = word;
            this.position = position;
            this.letterCount = letterCount;
        }

        public int getCacheNumber() {
            return cacheNumber;
        }

        public int getWord() {
            return word;
        }

        public int getPosition() {
            return position;
        }

        public int getLetterCount() {
            return letterCount;
        }
    }
}
