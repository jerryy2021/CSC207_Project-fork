package main.java.interface_adapter;

import main.java.use_case.OpenNotesOutputBoundary;
import main.java.use_case.OpenNotesOutputData;

public class OpenNotesPresenter implements OpenNotesOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private final NotesViewModel notesViewModel;

    public OpenNotesPresenter(ViewManagerModel viewManagerModel,
                              NotesViewModel notesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.notesViewModel = notesViewModel;
    }

    @Override
    public void presentNotes(OpenNotesOutputData data) {
        NotesState notesState = notesViewModel.getState();
        notesState.setNotes(data.getNotes());
        notesViewModel.setState(notesState);
        notesViewModel.firePropertyChanged(); //TODO: Implement NotesView's PropertyChange method that gets called after this

        viewManagerModel.setActiveView(notesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}