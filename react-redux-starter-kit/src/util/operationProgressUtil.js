import { store } from '../Root'
import { startOperationProgress, endOperationProgress } from '../actions/operationProgressActions'

export const showProgressDialog = () => {
  store.dispatch(startOperationProgress())
}

export const closeProgressDialog = () => {
  store.dispatch(endOperationProgress())
}
