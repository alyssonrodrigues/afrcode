import React from 'react'
import { shallow } from 'enzyme'

import { AppMenu } from './AppMenu'

describe('AppMenu', () => {
  it('AppMenu render deveria ocorrer sem falhas!', () => {
    const componentWrapper = shallow(
      <AppMenu classes={{}} theme={{}}
        authentication={{isUserAuthenticated: true}}
        mainMenuOpen={jest.fn()} handleMainMenuClose={jest.fn()} />
    )
    expect(componentWrapper).toMatchSnapshot()
  })
})
