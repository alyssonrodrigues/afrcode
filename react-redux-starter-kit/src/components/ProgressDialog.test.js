import React from 'react'
import { shallow } from 'enzyme'

import { ProgressDialog } from './ProgressDialog'

describe('ProgressDialog', () => {
  it('ProgressDialog render deveria ocorrer sem falhas!', () => {
    const componentWrapper = shallow(
      <ProgressDialog classes={{}} operationProgress={{operationInProgress: true}} />
    )
    expect(componentWrapper).toMatchSnapshot()
  })
})
