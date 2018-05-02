import Enzyme from 'enzyme'
import Adapter from 'enzyme-adapter-react-16'
import configureStore from 'redux-mock-store'

Enzyme.configure({ adapter: new Adapter() })

const middlewares = []
const mockReduxStore = configureStore(middlewares)

global.mockReduxStore = mockReduxStore

class LocalStorageMock {
  constructor () {
    this.store = {}
  }

  getItem (key) {
    return this.store[key]
  }

  setItem (key, value) {
    this.store[key] = value
  }

  removeItem (key) {
    this.store[key] = null
  }

  clear () {
    this.store = {}
  }
}

global.localStorage = new LocalStorageMock()
