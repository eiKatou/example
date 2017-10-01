package datetime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class Main {

	public static void main(String[] args) {
		// 日付の生成
		LocalDateTime ldate1 = LocalDateTime.now();
		LocalDateTime ldate2 = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
		LocalDateTime ldate3 = LocalDateTime.of(2017, Month.OCTOBER, 3, 15, 50);
		LocalDateTime ldate4 = LocalDateTime.of(2017, Month.OCTOBER, 5, 16, 24, 45, 500_000_000);
		LocalDateTime ldate5 = LocalDateTime.parse("2017-08-12T00:00");
		LocalDateTime ldate6 = LocalDateTime.parse("2017-08-13T12:12:35");
		LocalDateTime ldate7 = LocalDateTime.parse("2017-08-14T12:12:35.63822");
		System.out.println("ldate1 : " + ldate1);
		System.out.println("ldate2 : " + ldate2);
		System.out.println("ldate3 : " + ldate3);
		System.out.println("ldate4 : " + ldate4);
		System.out.println("ldate5 : " + ldate5);
		System.out.println("ldate6 : " + ldate6);
		System.out.println("ldate7 : " + ldate7);

		// 時間情報の生成
		LocalTime ltime1 = LocalTime.now();
		LocalTime ltime2 = LocalTime.of(12, 13);
		LocalTime ltime3 = LocalTime.of(12, 13, 45);
		System.out.println("ltime1 : " + ltime1);
		System.out.println("ltime2 : " + ltime2);
		System.out.println("ltime3 : " + ltime3);

		// 年や月や時刻などの設定
		LocalDateTime ldate4a = ldate4.withYear(1999);
		LocalDateTime ldate4b = ldate4.withMonth(3);
		LocalDateTime ldate4c = ldate4.with(ltime1);
		System.out.println("ldate4 withYear : " + ldate4a);
		System.out.println("ldate4 withMonth : " + ldate4b);
		System.out.println("ldate4 with : " + ldate4c);

		// 月の最終日などの設定
		LocalDateTime ldate1a = ldate1.with(TemporalAdjusters.firstDayOfMonth());
		LocalDateTime ldate1b = ldate1.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println("ldate1a firstDayOfMonth : " + ldate1a);
		System.out.println("ldate1b lastDayOfMonth : " + ldate1b);

		// 年や月などの値の取得
		System.out.println("ldate1 year : " + ldate1.getYear());
		System.out.println("ldate1 month : " + ldate1.getMonth());
		System.out.println("ldate1 dayOfWeek : " + ldate1.getDayOfWeek());

		// 加減算
		LocalDateTime ldate10 = LocalDateTime.now();
		LocalDateTime ldate11 = ldate10.plusYears(2);
		LocalDateTime ldate12 = ldate10.plusDays(3);
		LocalDateTime ldate13 = ldate10.minusDays(1);
		LocalDateTime ldate14 = ldate10.minusYears(5);
		System.out.println("ldate10 : " + ldate10);
		System.out.println("ldate11 : " + ldate11);
		System.out.println("ldate12 : " + ldate12);
		System.out.println("ldate13 : " + ldate13);
		System.out.println("ldate14 : " + ldate14);

		// 比較
		LocalDateTime ldate20 = LocalDateTime.parse("2017-08-13T12:12:35");
		LocalDateTime ldate21 = LocalDateTime.parse("2017-08-15T13:10:35");
		LocalDateTime ldate22 = LocalDateTime.parse("2017-08-13T12:12:35");
		System.out.println("ldate20 isAfter ldate21 : " + ldate20.isAfter(ldate21));
		System.out.println("ldate20 isBefore ldate21 : " + ldate20.isBefore(ldate21));
		System.out.println("ldate20 isEqual ldate21 : " + ldate20.isEqual(ldate21));
		System.out.println("ldate20 isEqual ldate22 : " + ldate20.isEqual(ldate22));

		// フォーマッタ
		LocalDateTime ldate30 = LocalDateTime.now();
		System.out.println("ldate30 : " + ldate30.format(DateTimeFormatter.ISO_DATE));
		System.out.println("ldate30 : " + ldate30.format(DateTimeFormatter.ISO_DATE_TIME));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy'年'MM'月'dd'日 'HH'時'mm'分'ss'秒'");
		System.out.println("ldate30 : " + ldate30.format(formatter));
		LocalDateTime ldate31 = LocalDateTime.parse("2016年11月07日 16時26分22秒", formatter);
		System.out.println("ldate31 : " + ldate31);

		// 間隔（Duration）
		Duration duration1 = Duration.ofDays(2);
		LocalDateTime ldate40 = LocalDateTime.now().plus(duration1);
		LocalDateTime ldate41 = LocalDateTime.now().minus(Duration.ofHours(30));
		System.out.println("ldate40 : " + ldate40);
		System.out.println("ldate41 : " + ldate41);

		// 間隔をbetweenメソッドで生成
		LocalDateTime ldate42 = LocalDateTime.parse("2017-08-13T12:12:35");
		LocalDateTime ldate43 = LocalDateTime.parse("2017-08-13T13:12:35");
		Duration duration2 = Duration.between(ldate42, ldate43);
		System.out.println("Duration2 : " + duration2.getSeconds());


		// 間隔（Period）
		Period period1 = Period.of(1, 2, 3);
		LocalDateTime ldate50 = LocalDateTime.now();
		LocalDateTime ldate51 = LocalDateTime.now().plus(period1);
		Period period2 = Period.between(ldate50.toLocalDate(), ldate51.toLocalDate());
		System.out.println("ldate50(now) : " + ldate50);
		System.out.println("ldate51 : " + ldate51);
		System.out.println("ldate50 between ldate51 : " + period2.toString());


		// OffsetDateTime
		OffsetDateTime odate1 = OffsetDateTime.of(2017, 9, 5, 16, 24, 45, 500_000_000, ZoneOffset.of("+09:00"));
		OffsetDateTime odate2 = odate1.withOffsetSameInstant(ZoneOffset.UTC);
		OffsetDateTime odate3 = odate1.withOffsetSameInstant(ZoneOffset.of("-08:00"));
		System.out.println("odate1 : " + odate1);
		System.out.println("odate2 : " + odate2); // UTC時間
		System.out.println("odate3 : " + odate3);
		System.out.println("odate3 offset : " + odate3.getOffset());
	}

}
