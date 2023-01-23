import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css'
import TodoBoard from './Components/TodoBoard'

export default function App() {
  const [item, setItem] = useState('');
  const [todoList, setTodoList] = useState([]);
  const [complete, setComplete] = useState([]);
    // useEffect(함수, 배열) : 컴포넌트가 화면에 나타났을 때 자동 실행
    useEffect(() => {
        //DB 데이터 -> todoList 배열로 옮기기
        axios
            .get("/getdata")
            .then((response)=>{
                setTodoList(response.data.map(value=>value[0]));
                setComplete(response.data.map(value=>value[1]));
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);
  function Send(){
    axios
        .post("/todo", {
          content: item,
          complete: 0
        })
        .catch((error) => {
          console.log(error);
        });
  }
  function addItem() {

    if (todoList.includes(item)) {
      alert(item + " 항목이 이미 존재합니다");
      setItem('');
    }

    else if(item === '') {
      alert("내용을 입력해주세요");
    }

    else{
      setTodoList([...todoList, item]);
      Send();
      setItem('');
    }
  }
  function checkEnterPressed(e) {
    if (e.keyCode === 13) {
      addItem();
    }
  }

  return (

    <main>
      <div>
        <h1>Shared To do List</h1>
        <hr></hr>
      </div>
      <input value={item} type='text' onChange={(event) => {  setItem(event.target.value) }} onKeyDown={checkEnterPressed}></input> <button onClick={addItem}>추가</button>

      <TodoBoard itemList={todoList} completeList={complete} setTodoList={setTodoList} setComplete={setComplete}/>

    </main>
  )
}
