import { combineReducers } from "redux";
import BooksReducer from "./reducer_books";
import ActiveBook from "./reducer_active_book";

// Each reducer is reponsible for creating a single piece of
// the application state.
const rootReducer = combineReducers({
  books: BooksReducer, // reduces aplication state.books
  activeBook: ActiveBook // reduces application state.activeBook
});

export default rootReducer;
