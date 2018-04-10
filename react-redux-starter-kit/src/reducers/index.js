import { combineReducers } from 'redux'
import { reducer as formReducer } from 'redux-form'

import authentication from './authenticationJwtReducer'

const rootReducer = combineReducers({
  // Adicione reducers aqui...
  authentication,
  form: formReducer // forms reducer, necessário para integração com o Redux form...
})

export default rootReducer
