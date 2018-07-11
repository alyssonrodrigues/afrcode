import React from 'react'
import { shallow } from 'enzyme'

import { NotFoundPage } from './NotFoundPage'

describe('NotFoundPage', () => {
  it('NotFoundPage render deveria ocorrer sem falhas!', () => {
    const componentWrapper = shallow(
      <NotFoundPage />
    )
    expect(componentWrapper).toMatchSnapshot()
  })
})
