package Controller;

import Model.Playlist;
import Model.PlaylistManager;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import sample.addPlaylistView;

import java.io.File;

/**
 * Created by Pascal on 06.12.2017.
 */
public class AddPlaylistController {

    private PlaylistManager playlistManager = new PlaylistManager();
    private addPlaylistView view;

    public AddPlaylistController(addPlaylistView view)
    {
        this.view = view;
    }

    public EventHandler<MouseEvent> btn_ok_mouseclick = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            String value = view.getTextFieldValue();
            if (value != null) {
                File playlist = new File(System.getProperty("user.home").concat("//Music" + "/" + value +".m3u"));
                playlistManager.getPlaylists().add(new Playlist(value, playlist.getPath()));
                playlistManager.createEmptyPlaylist(playlist);
                view.stop();
            }
            else {
                view.invokeErrorMessage();
            }
        }
    };
    public EventHandler<MouseEvent> btn_cancel_mouseclick = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            view.stop();
        }
    };




}
