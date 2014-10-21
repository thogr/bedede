package com.github.thogr.bedede.examples.door;

public class Door {

    private boolean open = false;
    private boolean locked = true;
    private final int code;

    public Door(final int code) {
        this.code = code;
    }

    public void turnKey(final Key key) {
        if (isCorrectKey(key)) {
            this.locked = !isLocked();
        }
    }

	private boolean isCorrectKey(final Key key) {
		return code == key.getCode();
	}

    public boolean isLocked() {
        return locked;
    }

    public void open() {
        if (!isLocked()) {
            this.open = true;
        }
    }

    public void close() {
        if (!isLocked()) {
            this.open = false;
        }
    }

    public boolean isOpen() {
        return open;
    }
}
