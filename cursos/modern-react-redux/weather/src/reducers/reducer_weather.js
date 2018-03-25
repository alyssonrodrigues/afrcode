import { FETCH_WEATHER } from "../actions/index";

export default function(state = [], action) {
  switch (action.type) {
    case FETCH_WEATHER:
      // reducers must return a new fresh state object with new data added!
      return [action.payload.data, ...state];
  }
  return state;
}
