package com.company;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 * Created by 15 on 19.01.2018.
 */
public class Library {
//    создаю 2 коллекции для хранения всех учеников и изданий соответственно
    public static ArrayList<Pupil> pupils = new ArrayList<>();
    public static ArrayList<Publication> listPublication = new ArrayList<>();
//    создаю переменную для запуска потока с основной логикой приложения
    public static Library game;

    public static void main(String[] args) throws InterruptedException {
        game = new Library();

//      добавляю в коллекцию учеников 5 человек
        pupils.add(new Pupil("Steven", "Gerrard",0.2, 0, 0,
                false, 0, new GregorianCalendar(1980, Calendar.MAY, 30)));
        pupils.add(new Pupil("Frank", "Lampard",0.1, 0, 0,
                false,0, new GregorianCalendar(1978, Calendar.JUNE, 20)));
        pupils.add(new Pupil("Alan", "Shearer",0.4, 0, 0,
                false,0, new GregorianCalendar(1970, Calendar.AUGUST, 13)));
        pupils.add(new Pupil("Luis", "Figo",0.3, 0, 0,
                false,0, new GregorianCalendar(1972, Calendar.NOVEMBER, 4)));
        pupils.add(new Pupil("Zinedine", "Zidane",0.5, 0, 0,
                false,0, new GregorianCalendar(1972, Calendar.JUNE, 23)));

//      добавляю в коллекцию изданий 16 публикаций
        listPublication.add(new Publication(1,"Book","Book1",true));
        listPublication.add(new Publication(2,"Book","Book2",true));
        listPublication.add(new Publication(3,"Book","Book3",true));
        listPublication.add(new Publication(4,"Book","Book4",true));
        listPublication.add(new Publication(5,"Book","Book5",true));
        listPublication.add(new Publication(6,"Book","Book6",true));
        listPublication.add(new Publication(7,"Book","Book7",true));
        listPublication.add(new Publication(8,"Book","Book8",true));
        listPublication.add(new Publication(9,"Book","Book9",true));
        listPublication.add(new Publication(10,"Book","Book10",true));

        listPublication.add(new Publication(11,"Article","Article1",true));
        listPublication.add(new Publication(12,"Article","Article2",true));

        listPublication.add(new Publication(13,"Journal","Journal1",true));
        listPublication.add(new Publication(14,"Journal","Journal2",true));

        listPublication.add(new Publication(15,"Newspaper","Newspaper1",true));
        listPublication.add(new Publication(16,"Newspaper","Newspaper2",true));

//        При запуске предлагается выбрать отчет
        System.out.println("Введите, пожалуйста, номер отчета из списка, который Вы хотите увидеть");
        System.out.println("1. Отчет о доступных для чтения книгах, статьях, журналах и газетах");
        System.out.println("2. Отчет об учениках, которые прочитали более 1 книги");
        System.out.println("3. Отчет об учениках, которые прочитали не более 2 книг");

        game.run();
    }

    public void run() throws InterruptedException
    {
//        создание и запуск "наблюдателя"
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();
        for (int i=0; i<1000;i++)
        {
            read();
            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents())
            {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //Если нажать "1" - вывести первый отчет
                if (event.getKeyCode() == KeyEvent.VK_1)
                    printListOfAvailableForReading();
                    //Если нажать "2" - вывести второй отчет
                else if (event.getKeyCode() == KeyEvent.VK_2)
                    printReportPupilsMoreThan1Book();
                    //Если нажать "3" - вывести третий отчет
                else if (event.getKeyCode() == KeyEvent.VK_3)
                    printReportPupilsMoreThan2Book();
            }
            Thread.sleep(1000);
        }
    }

    public void read() {
        for (Pupil pupil : pupils) {
// если ученик не читает в настоящее время, то пусть попытается выбрать книгу
            if (!pupil.getReading()){
                int numberBookReadNow = choicePublication();
// если он выбрал свободную книгу, т.е. номер книги!=0, изменить соотв поля
                if (numberBookReadNow != 0) {
                    pupil.setReading(true);
                    pupil.setNumberBookReadNow(numberBookReadNow);
                }
            }
//  если у ученика уже есть книга
            else {
//  если он уже дочитал
                if (pupil.getPartBook()>=1.0){
//  если прочитал именно книгу, а не что-то иное - добавляем к числу прочитанных книг 1
                    if ( listPublication.get(pupil.getNumberBookReadNow()).getTypePublication().equals("Book")) {
                        pupil.setNumberBooksRead(pupil.getNumberBooksRead() + 1);
                    }
//  ученик "сдает" книгу в библиотеку, т.е. на след итерации должен попытаться взять новую
                    pupil.setReading(false);
//  книга становится опять доступной для всех
                   listPublication.get(pupil.getNumberBookReadNow()).setAvailablePublication(true);
                }
//  если не дочитал, читает дальше
                else  pupil.read();
            }
        }
    }

//  метод для выбора книги
    public Integer choicePublication() {
//  из списка выбирается случайная книга
       double numberPublication= listPublication.size()*Math.random();
//  если публикация свободна, то возвращается ее номер
       if (listPublication.get((int)numberPublication).getAvailablePublication()){
           listPublication.get((int)numberPublication).setAvailablePublication(false);
           return (int)numberPublication;
       }
//  иначе возвращается 0
       else return 0;
    }

// метод для вывода отчета о всех изданиях
    public void printListOfAvailableForReading(){
        System.out.println();
        System.out.println("Отчет о доступных для чтения книгах, статьях, журналах и газетах");
        System.out.println("№ по списку     Тип издания      Название  В библиотеке / у ученика");
// для вывода если издание находится в библиотеке
        for (int i=0;i<listPublication.size();i++){
            if (listPublication.get(i).getAvailablePublication()) {
                System.out.println("      "+listPublication.get(i).getNumberPublication() + "              " +
                        listPublication.get(i).getTypePublication() + "         " +
                       listPublication.get(i).getNamePublication() + "          В библиотеке");
            }
// для вывода если издание находится у ученика
        else {
                System.out.println("      "+listPublication.get(i).getNumberPublication() + "              " +
                        listPublication.get(i).getTypePublication() + "         " +
                        listPublication.get(i).getNamePublication() + "          У ученика");
            }
        }
    }

//    метод для вывода отчета о учениках, прочитавшив более одной книги
    public void printReportPupilsMoreThan1Book(){
        System.out.println();
        System.out.println("Отчет об учениках, которые прочитали более 1 книги");
//   сортировка коллекции по возрастанию кол-ва прочитанных книг
        Collections.sort(pupils, new NumberBooksReadComporator());
        for (int j=0; j<pupils.size(); j++)
        {
            if(pupils.get(j).getNumberBooksRead()>1){
                System.out.println(pupils.get(j).getName()+" "+pupils.get(j).getLastName()+": "+pupils.get(j).getNumberBooksRead());}
        }
    }

//    метод для вывода отчета о учениках, прочитавшив более 2 книг
    public void printReportPupilsMoreThan2Book(){
        System.out.println();
        System.out.println("Отчет об учениках, которые прочитали более 2 книг");
//   сортировка коллекции сначала по дате рождения от самого старого к молодому, затем по возрастанию кол-ва прочитанных книг
        Collections.sort(pupils, new CalendarComparator());
        Collections.sort(pupils, new NumberBooksReadComporator());
        for (int j=0; j<pupils.size(); j++) {
            if (pupils.get(j).getNumberBooksRead() > 2) {
                System.out.println(pupils.get(j).getName() + " " + pupils.get(j).getLastName() + ", " +
                        pupils.get(j).getDateBirth().get(Calendar.YEAR) + "." + pupils.get(j).getDateBirth().get(Calendar.MONTH)
                        + "." + pupils.get(j).getDateBirth().get(Calendar.DAY_OF_MONTH)+": "+pupils.get(j).getNumberBooksRead());
            }
        }

    }

}
