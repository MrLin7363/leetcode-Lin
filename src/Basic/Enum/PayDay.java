package Basic.Enum;

import java.util.EnumSet;

/**
 * @author: Junlin Chen
 * @Date: 2021/06/12 17:55
 * @Describe: 计算工资，周六日，或者周一到周五超过8小时的时间，算一半加班工资
 * 策略枚举
 * EunmSet , EnumMap
 */
public enum PayDay {
    MONDAY(PayType.WEEKDAY),
    TUESDAY(PayType.WEEKDAY),
    SUNDAY(PayType.WEEKEND);

    PayDay(PayType payType) {
        this.payType = payType;
    }

    double pay(double hoursWorked, double payRate) {
        return payType.pay(hoursWorked, payRate);
    }

    private final PayType payType;

    private enum PayType { // 也可以通过接口的方式加上方法
        WEEKDAY {
            @Override
            double overTimePay(double hours, double payRate) {
                return hours < normalWorkHours ? 0 :
                        (hours - normalWorkHours) * payRate / 2;
            }
        },
        WEEKEND {
            @Override
            double overTimePay(double hours, double payRate) {
                return hours * payRate / 2;
            }
        };
        private static final int normalWorkHours = 8;

        abstract double overTimePay(double hours, double payRate);

        double pay(double hoursWorked, double payRate) {
            double basePay = hoursWorked * payRate;
            return basePay + overTimePay(hoursWorked, payRate);
        }
    }

    public static void main(String[] args) {
        System.out.println(PayDay.SUNDAY.ordinal()); // 枚举的序数，最好不用这种
        System.out.println(
                PayDay.MONDAY.pay(9,1)
        );
    }
}
