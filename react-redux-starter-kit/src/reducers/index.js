import { combineReducers } from 'redux'

import messages from './messagesReducer'
import authentication from './authenticationJwtReducer'
import operationProgress from './operationProgressReducer'
import mainMenu from './mainMenuReducer'

const rootReducer = combineReducers({
  // Adicione reducers aqui...
  messages,
  authentication,
  operationProgress,
  mainMenu
})

export default rootReducer
