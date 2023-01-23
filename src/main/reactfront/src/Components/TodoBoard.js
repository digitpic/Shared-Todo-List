import React, {useEffect} from 'react';
import TodoItem from './TodoItem';
import axios from "axios";

function TodoBoard(props) {

  return (
    <div>
      {
        props.itemList.map(
          (item, idx) => <TodoItem key={idx} num={idx} item={item} todoList={props.itemList} completeList={props.completeList} setComplete={props.setComplete}/>
        )
      }
    </div>
  )
}

export default TodoBoard;
