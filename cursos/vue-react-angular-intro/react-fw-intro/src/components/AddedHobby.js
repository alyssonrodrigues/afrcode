import React from 'react';

function AddedHobby(props) {
  return (
    <li 
      onClick={() => props.deleteHobby(props.hobby)}>
      {props.hobby}
    </li>
  );
}

export default AddedHobby;