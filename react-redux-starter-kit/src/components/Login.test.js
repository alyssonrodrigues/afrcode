/* eslint-env jest */
import React from 'react'
import renderer from 'react-test-renderer'
import { Provider } from 'react-redux'

import Login from './Login'

describe('<Login />', () => {
  it('Login snapshot deveria ocorrer sem falhas!', () => {
    const store = mockReduxStore({ authentication: { isUserAuthenticated: false } })
    const tree = renderer.create((
      <Provider store={store}>
        <Login store={store} location={{}} />
      </Provider>
    )).toJSON()
    expect(tree).toMatchSnapshot()
  })
})
