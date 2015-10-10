package com.github.thogr.bedede.examples.presenter;

import static com.github.thogr.bedede.Expressions.a;
import static com.github.thogr.bedede.Expressions.given;
import static com.github.thogr.bedede.Expressions.performing;
import static com.github.thogr.bedede.Expressions.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.Test;

public class PresenterExampleTest {

    static interface FileSelectorView {
        FileSelectorPresenter getPresenter();
        boolean isOpened();
        void setPresenter(FileSelectorPresenter fileSelectorPresenter);
        void open();
    }

    static class FileSelectorStub implements FileSelectorView {

        private FileSelectorPresenter presenter;
        private boolean opened;

        @Override
        public FileSelectorPresenter getPresenter() {
            return presenter;
        }

        @Override
        public boolean isOpened() {
            return opened;
        }

        @Override
        public void setPresenter(final FileSelectorPresenter presenter) {
            this.presenter = presenter;
        }

        @Override
        public void open() {
            this.opened = true;
        }
    }

    static class FileLoader {

    }

    static class FileSelectorPresenter {
        private final FileSelectorView view;

        FileSelectorPresenter(final FileSelectorView view) {
            this.view = view;

        }

        public void start() {
            view.setPresenter(this);
            view.open();
        }
    }

    @Test
    public void testName() throws Exception {
        given(a(new FileSelectorStub()), stub ->
        given(a(new FileLoader()), loader ->
        given(a(new FileSelectorPresenter(stub)), presenter ->

        when(performing(the -> presenter.start()))

        .then(the -> stub.getPresenter(), is(notNullValue()))
        .then(the -> stub.isOpened()))));
    }
}
