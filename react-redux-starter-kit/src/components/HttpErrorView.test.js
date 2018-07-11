import React from 'react'
import { shallow } from 'enzyme'

import { HttpErrorView } from './HttpErrorView'

describe('HttpErrorView', () => {
  it('HttpErrorView render deveria ocorrer sem falhas!', () => {
    const componentWrapper = shallow(
      <HttpErrorView classes={{}} messages={{}} dismissMessage={jest.fn()} />
    )
    expect(componentWrapper).toMatchSnapshot()
  })
})
