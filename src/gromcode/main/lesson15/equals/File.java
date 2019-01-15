package gromcode.main.lesson15.equals;

public class File {
    private int size;
    private String path;
    private String extension;

    public File(int size, String path, String extension) {
        this.size = size;
        this.path = path;
        this.extension = extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return path.equals(file.path);
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        File file = (File) o;
//        return size == file.size &&
//                Objects.equals(path, file.path) &&
//                Objects.equals(extension, file.extension);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(size, path, extension);
//    }
}
