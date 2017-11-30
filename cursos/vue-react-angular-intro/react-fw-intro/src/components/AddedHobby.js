import React from 'react';

function AddedHobby(props) {
  return (
    <li 
      onClick={() => props.removeHobby(props.hobby)}>
      {props.hobby}
    </li>
  );
}

export default AddedHobby;