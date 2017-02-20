package NPI.Helpers;

public class ChangeLog {

	public static String getChangeLog(){
		String log = "<html>"
					+"<p>[v1.1.0. от 30.01.2017] Добавлен лог изменений</p>"
				    +"<p>[v1.1.0. от 30.01.2017] При трекинге теперь записывается поле RecipientDateTime</p>"
					+"<p>[v1.2.0. от 20.02.2017] Добавлена возможность формировать сжатую версию трекинга</p>"
					+"</html>";
		return log;

	}
}
