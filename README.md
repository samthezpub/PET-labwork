![logo](https://i.ibb.co/yPQ59hM/VLiP.jpg)

# О проекте

Данная виртуальная лабораторная работа представляет собой компьютерную программу-симулятор, моделирующую основные этапы выполнения лабораторной работы "Проверка уравнения Ван-дер-Ваальса" с использованием различного лабораторного оборудования. С её помощью ученики отрабатывают основные действия, умения и навыки, которые необходимы при выполнении лабораторной работы.

# Функционал
Условная установка (балон с газом в термостате) снабжена **термометром** и **манометром** . При переводе курсора на них активируются соответствующие шкалы. Параллельно цифровые значения давления и температуры выводятся на экран.
В окне "Настройка" предлагается выбрать **наименование** газа, его **массу** и **количество знаков после запятой. Скорость нагрева** регулируется **ползунком в рабочем окне программы**.

  ![labwork](https://github.com/samthezpub/labwork/assets/70057256/0eb6223b-e39e-4e71-948c-aa18978a676b)

# Установка и запуск
Программа представляет из себя exe файл.

Для того чтобы запустить программу необходимо установить JRE 8 на компьютер. Вы можете скачать её в нашем релизе.

# Важные моменты
## В классе HelloApplicaton находится точка входа в приложение. В этом классе так же запускается первая сцена - MainForm
```java

public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Проверка уравнения Ван-дер-Вальса");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Устанавливает иконку
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/pictures/aboutlogo.png")));
    }
```

## В MainFormController происходят основные переходы на другие формы
```java
    void graphic_clicked(ActionEvent event) {
        graphicStage.show();
        graphicStage.setResizable(false);
      }

    void manual_clicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manual.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Методичка");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);

            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/pictures/aboutlogo.png")));

            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
```
и т.д.

# Разработчики

- [samthezpub](https://github.com/samthezpub)
- [MrDrag0nXYT](https://github.com/MrDrag0nXYT)
- [KOSTOF](https://github.com/KOSTOF)
- [Artem-Prots](https://github.com/Artem-Prots)
- [Elikaka2](https://github.com/Elikaka2)
- [J1yNa](https://github.com/J1yNa)
