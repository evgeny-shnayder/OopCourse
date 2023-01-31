package Range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double rangeLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getCrossingRange(Range range2) {
        double range1Length = this.rangeLength();
        double range2Length = range2.rangeLength();
        double range1Start = this.getFrom();
        double range2Start = range2.getFrom();
        double range1End = this.getTo();
        double range2End = range2.getTo();

        if (range1Start == range2Start && range1End == range2End) {
            return this;
        } else if (range1Start < range2Start && range1End > range2End || range1Start > range2Start && range1End < range2End) {
            return range1Length > range2Length ? this : range2;
        } else if (range1Start > range2Start && range1Start < range2End || range2End > range1Start && range2End < range1End) {
            return new Range(range1Start, range2End);
        } else if (range1End > range2Start && range1End < range2End || range2Start > range1Start && range2Start < range1End) {
            return new Range(range2Start, range1End);
        }

        return null;
    }

    public Range[] getUnionRange(Range range2) {
        double range1Length = this.rangeLength();
        double range2Length = range2.rangeLength();
        double range1Start = this.getFrom();
        double range2Start = range2.getFrom();
        double range1End = this.getTo();
        double range2End = range2.getTo();

        if (range1Start == range2Start && range1End == range2End) {
            return new Range[]{this};
        } else if (range1Start < range2Start && range1End > range2End || range1Start > range2Start && range1End < range2End) {
            return range1Length > range2Length ? new Range[]{this} : new Range[]{range2};
        } else if (range1Start >= range2Start && range1Start <= range2End || range2End >= range1Start && range2End <= range1End) {
            return new Range[]{new Range(range2Start, range1End)};
        } else if (range1End >= range2Start && range1End <= range2End || range2Start >= range1Start && range2Start <= range1End) {
            return new Range[]{new Range(range1Start, range2End)};
        }

        return new Range[]{this, range2};
    }

    Range[] getNewRanges(double range1Start, double range1End, double range2Start, double range2End) {
        return new Range[]{new Range(range1Start, range1End), new Range(range2Start, range2End)};
    }

    public Range[] getRangeDifference(Range range2) {
        double range1Length = this.rangeLength();
        double range2Length = range2.rangeLength();
        double range1Start = this.getFrom();
        double range2Start = range2.getFrom();
        double range1End = this.getTo();
        double range2End = range2.getTo();

        if (range1Start == range2Start && range1End != range2End || range1Start != range2Start && range1End == range2End) {
            if (range1Length > range2Length) {
                return range1Start == range2Start ? new Range[]{new Range(range2End, range1End)}
                        : new Range[]{new Range(range1Start, range2Start)};
            }
        } else if (range2Start > range1Start && range2Start < range1End || range2Start < range1Start && range2End > range1Start) {
            if (range1Length < range2Length) {
                return null;
            } else if (range2Start < range1Start) {
                return range2End < range1End ? getNewRanges(range2Start, range1Start, range2End, range1End)
                        : getNewRanges(range2Start, range1Start, range1End, range2End);
            }

            return range2Length < range1End - range2Start ? getNewRanges(range1Start, range2Start, range2End, range1End)
                    : getNewRanges(range1Start, range2Start, range1End, range2End);
        }

        return null;
    }
}
