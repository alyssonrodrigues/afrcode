import { combineReducers } from 'redux'
import { reducer as formReducer } from 'redux-form'

import messages from './messagesReducer'
import authentication from './authenticationJwtReducer'
import operationProgress from './operationProgressReducer'

const rootReducer = combineReducers({
  // Adicione reducers aqui...
  messages,
  authentication,
  operationProgress,
  form: formReducer // forms reducer, necessário para integração com o Redux form...
})

export default rootReducer
