package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class Controller {
    private static Boolean isPlaying = false;
    private static MediaPlayer mediaPlayer;
    private final double k = 1;
    private static File track;
    private static InputStream inputTrack;

    static File getTrack() {
        return track;
    }

    @FXML
    Button play;

    @FXML
    public void playMusic() {
        if (mediaPlayer != null) {
            enableOrDisableEqualizer();
            setVolume();
            setBand0();
            setBand1();
            setBand2();
            setBand3();
            setBand4();
            setBand5();
            setBand6();
            setBand7();
            setBand8();
            setBand9();
            monitorPlayTime();
            if (!isPlaying) {
                isPlaying = true;
//                SoundPlayer sound = new SoundPlayer(track);
//                InputStream stream = new ByteArrayInputStream(sound.getSamples());
//                SoundPlayer.play(stream);
                mediaPlayer.play();
                play.setText("Pause");
            } else {
                mediaPlayer.pause();
                isPlaying = false;
                play.setText("Play");
            }
        }
    }

    @FXML
    Slider seekSlider;
    @FXML
    Label currentPlayTime;

    @FXML
    private void monitorPlayTime() {
        mediaPlayer.currentTimeProperty().addListener(ov -> updatesValues());
        // Чтобы перематывать трек к значению слайдера
        seekSlider.valueProperty().addListener(ov -> {
            if (seekSlider.isPressed()) {
                // Устанавливает время, зависящее от слайдера
                mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(seekSlider.getValue() / 100));
            }
        });
    }

    private void updatesValues() {
        double trackDuration = mediaPlayer.getMedia().getDuration().toSeconds();
        int minutes, seconds;
        minutes = (int) trackDuration / 60;
        seconds = (int) trackDuration - minutes * 60;
        String durationOfTrack = minutes + "." + seconds;
        Platform.runLater(() -> {
            // Updating to the new time value
            // This will move the slider while running your video
            seekSlider.setValue(mediaPlayer.getCurrentTime().toMillis() / mediaPlayer.getTotalDuration().toMillis() * 100);
            currentPlayTime.setText((int) mediaPlayer.getCurrentTime().toSeconds() / 60 + "." + (int) mediaPlayer.getCurrentTime().toSeconds() % 60 + "/" + durationOfTrack);
        });
    }

    @FXML
    Slider volume;

    @FXML
    private void setVolume() {
        volume.valueProperty().addListener(ov -> {
            if (volume.isValueChanging()) {
                mediaPlayer.setVolume(volume.getValue() / 100.0);
            }
        });
    }


    @FXML
    Button openFile;

    @FXML
    public void openDialog() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Аудиофайлы", "*.mp3", "*.wav");
        fileChooser.getExtensionFilters().add(extensionFilter);
        openFile.setOnAction(event -> {
            track = fileChooser.showOpenDialog(openFile.getScene().getWindow());
            if (track != null) {
                trackInfo(track);
                Controller.mediaPlayer = new MediaPlayer(new Media(track.toURI().toString()));
                try {
                    Serialize.serialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    Label trackName;

    @FXML
    private void trackInfo(File track) {
        trackName.setText("Трек: " + track.getName().substring(0, track.getName().length() - 4));
    }

    @FXML
    Slider band0;
    @FXML
    Label band0gain;

    @FXML
    private void setBand0() {
        band0.valueProperty().addListener(ov -> {
            if (band0.isValueChanging()) {
                mediaPlayer.getAudioEqualizer().getBands().get(0).setGain(band0.getValue() * k);
                band0gain.setText(new DecimalFormat("#0.0").format(mediaPlayer.getAudioEqualizer().getBands().get(0).getGain()) + "dB");
            }
        });
    }

    @FXML
    Slider band1;
    @FXML
    Label band1gain;

    @FXML
    private void setBand1() {
        band1.valueProperty().addListener(ov -> {
            if (band1.isValueChanging()) {
                mediaPlayer.getAudioEqualizer().getBands().get(1).setGain(band1.getValue() * k);
                band1gain.setText(new DecimalFormat("#0.0").format(mediaPlayer.getAudioEqualizer().getBands().get(1).getGain()) + " dB");
            }
        });
    }

    @FXML
    Slider band2;
    @FXML
    Label band2gain;

    @FXML
    private void setBand2() {
        band2.valueProperty().addListener(ov -> {
            if (band2.isValueChanging()) {
                mediaPlayer.getAudioEqualizer().getBands().get(2).setGain(band2.getValue() * k);
                band2gain.setText(new DecimalFormat("#0.0").format(mediaPlayer.getAudioEqualizer().getBands().get(2).getGain()) + " dB");
            }
        });
    }

    @FXML
    Slider band3;
    @FXML
    Label band3gain;

    @FXML
    private void setBand3() {
        band3.valueProperty().addListener(ov -> {
            if (band3.isValueChanging()) {
                mediaPlayer.getAudioEqualizer().getBands().get(3).setGain(band3.getValue() * k);
                band3gain.setText(new DecimalFormat("#0.0").format(mediaPlayer.getAudioEqualizer().getBands().get(3).getGain()) + " dB");
            }
        });
    }

    @FXML
    Slider band4;
    @FXML
    Label band4gain;

    @FXML
    private void setBand4() {
        band4.valueProperty().addListener(ov -> {
            if (band4.isValueChanging()) {
                mediaPlayer.getAudioEqualizer().getBands().get(4).setGain(band4.getValue() * k);
                band4gain.setText(new DecimalFormat("#0.0").format(mediaPlayer.getAudioEqualizer().getBands().get(4).getGain()) + " dB");
            }
        });
    }

    @FXML
    Slider band5;
    @FXML
    Label band5gain;

    @FXML
    private void setBand5() {
        band5.valueProperty().addListener(ov -> {
            if (band5.isValueChanging()) {
                mediaPlayer.getAudioEqualizer().getBands().get(5).setGain(band5.getValue() * k);
                band5gain.setText(new DecimalFormat("#0.0").format(mediaPlayer.getAudioEqualizer().getBands().get(5).getGain()) + " dB");
            }
        });
    }

    @FXML
    Slider band6;
    @FXML
    Label band6gain;

    @FXML
    private void setBand6() {
        band6.valueProperty().addListener(ov -> {
            if (band6.isValueChanging()) {
                mediaPlayer.getAudioEqualizer().getBands().get(6).setGain(band6.getValue() * k);
                band6gain.setText(new DecimalFormat("#0.0").format(mediaPlayer.getAudioEqualizer().getBands().get(6).getGain()) + " dB");
            }
        });
    }

    @FXML
    Slider band7;
    @FXML
    Label band7gain;

    @FXML
    private void setBand7() {
        band7.valueProperty().addListener(ov -> {
            if (band7.isValueChanging()) {
                mediaPlayer.getAudioEqualizer().getBands().get(7).setGain(band7.getValue() * k);
                band7gain.setText(new DecimalFormat("#0.0").format(mediaPlayer.getAudioEqualizer().getBands().get(7).getGain()) + " dB");
            }
        });
    }

    @FXML
    Slider band8;
    @FXML
    Label band8gain;

    @FXML
    private void setBand8() {
        band8.valueProperty().addListener(ov -> {
            if (band8.isValueChanging()) {
                mediaPlayer.getAudioEqualizer().getBands().get(8).setGain(band8.getValue() * k);
                band8gain.setText(new DecimalFormat("#0.0").format(mediaPlayer.getAudioEqualizer().getBands().get(8).getGain()) + " dB");
            }
        });
    }

    @FXML
    Slider band9;
    @FXML
    Label band9gain;

    @FXML
    private void setBand9() {
        band9.valueProperty().addListener(ov -> {
            if (band9.isValueChanging()) {
                mediaPlayer.getAudioEqualizer().getBands().get(9).setGain(band9.getValue() * k);
                band9gain.setText(new DecimalFormat("#0.0").format(mediaPlayer.getAudioEqualizer().getBands().get(9).getGain()) + " dB");
            }
        });
    }

    @FXML
    ToggleButton equalizerOn;

    @FXML
    private void enableOrDisableEqualizer() {
        equalizerOn.setOnAction(event -> {
            if (equalizerOn.isSelected()) {
                equalizerOn.setText("Вкл.");
                mediaPlayer.getAudioEqualizer().setEnabled(false);
            } else {
                equalizerOn.setText("Выкл.");
                mediaPlayer.getAudioEqualizer().setEnabled(true);
            }
        });
    }

}
