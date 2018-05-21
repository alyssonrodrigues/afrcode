/* eslint-env jest */
import store from './configureStore'

describe('configureStore', () => {
  it('A redux store deveria ter sido criada sem erros!', () => {
    expect(store.getState()).toEqual(expect.anything())
  })
})
