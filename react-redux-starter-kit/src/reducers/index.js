import { combineReducers } from 'redux'
import { reducer as formReducer } from 'redux-form'

import messages from './messagesReducer'
import authentication from './authenticationJwtReducer'
import operationProgress from './operationProgressReducer'
import mainMenu from './mainMenuReducer'

const rootReducer = combineReducers({
  // Adicione reducers aqui...
  messages,
  authentication,
  operationProgress,
  mainMenu,
  form: formReducer // forms reducer, necessário para integração com o Redux form...
})

export default rootReducer
