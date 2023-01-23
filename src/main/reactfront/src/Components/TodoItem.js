import React, {useEffect} from 'react';
import axios from "axios";

function TodoItem(props) {

    useEffect(()=>{
        setColor();
        initArray();
    }, []);

    const changeColor = (event) => {
        if (event.target.checked) {

            event.target.defaultChecked=false;
            event.target.nextSibling.style.border = "2px solid green";
            event.target.nextSibling.style.textDecoration = "line-through";
            axios
                .post("/modifydata",{
                    content: event.target.nextSibling.innerHTML
                })
                .catch((error) => {
                    console.log(error);
                });
        }
        else {
            event.target.defaultChecked=true;
            event.target.nextSibling.style.border = "2px solid red";
            event.target.nextSibling.style.textDecoration = "none";
            axios
                .post("/modifydata",{
                    content: event.target.nextSibling.innerHTML
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }
  const delItem = (event) => {

    let parent = event.target.parentNode;

    //index 뽑기
    let idx = props.todoList.indexOf(props.item);

    //삭제
    if (idx !== -1) {
      props.todoList.splice(idx, 1);
    }

    //DOM 객체 정리
    parent.replaceChildren();

    //DB 데이터 삭제
      axios
          .post("/remove",{
              content: props.item
          })

          .catch((error) => {
              console.log(error);
          });
  }

  function returnBooleanType(str){
        if(str==="0"){
            return false;
        }
        else if(str==="1"){
            return true;
        }
  }

  const setColor = () => {
        if(returnBooleanType(props.completeList[props.num])){//true
            document.getElementById("list"+props.num).style.border="2px solid green"
            document.getElementById("list"+props.num).style.textDecoration="line-through"
        }
        else{//false
            document.getElementById("list"+props.num).style.border="2px solid red"
            document.getElementById("list"+props.num).style.textDecoration="none"
        }
  }

  const initArray = ()=> {
      let copyArr = props.completeList;
      copyArr[props.num]=0;
      props.setComplete(copyArr);
  }

  return (
    <div>
      <hr/>
      <input type="checkbox" onClick={changeColor} defaultChecked={returnBooleanType(props.completeList[props.num])}/>
      <li id={"list"+props.num} className='item'>{props.item}</li> <button onClick={delItem}>삭제</button>
      <br/>
    </div>
  )
}

export default TodoItem;