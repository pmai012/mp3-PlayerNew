package Model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PlaylistManager {

    public PlaylistManager()
    {

    }

    public List<Playlist> findPlaylist(String name){
        List<Playlist> playlist;
        return playlist = new List<Playlist>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Playlist> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Playlist playlist) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Playlist> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Playlist> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Playlist get(int index) {
                return null;
            }

            @Override
            public Playlist set(int index, Playlist element) {
                return null;
            }

            @Override
            public void add(int index, Playlist element) {

            }

            @Override
            public Playlist remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Playlist> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Playlist> listIterator(int index) {
                return null;
            }

            @Override
            public List<Playlist> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }
    public Playlist getAllTracks()
    {
        return new Playlist();
    }
    public void setPlaylist(Playlist actPlaylist){}
    public void deletePlaylist(Playlist actPlaylist){}
    public void updatePlaylist(Playlist actPlaylist){}
}
