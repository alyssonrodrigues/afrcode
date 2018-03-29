import { combineReducers } from 'redux';
import posts from './reducer_posts';
import selectedPostIds from './reducer_selected_posts';

const rootReducer = combineReducers({
  posts,
  selectedPostIds
});

export default rootReducer;
