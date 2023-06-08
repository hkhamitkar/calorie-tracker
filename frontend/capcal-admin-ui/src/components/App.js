import React, { useState, useEffect } from "react";
import { Routes, Route, useNavigate } from "react-router-dom"
import "./App.css"
import Header from "./admin/Header"
import AddFood from "./admin/AddFood"
import FoodList from "./admin/FoodList"
import api from "../api/api"
import EditFood from "./admin/EditFood";
import Report from "./admin/Report";




function App() {
  const [foodlist, setFoodList] = useState([]);
  const [admindata, setAdminData] = useState([]);

  const addFoodhandler = async (food) => {
    //food.id=uuid();
    const request = food;
    const response = await api.post("/foodlog/add", request)
    setFoodList([response.data, ...foodlist]);
  }

  const updateFoodHandler = async (food) => {
    const response = await api.put("/foodlog/update", food);
    const id = response.data.id;
    setFoodList(foodlist.map((food) => {
      return food.id === id ? { ...response.data } : food;
    })
    );
  };

  const searchHandler = async (searchdata) => {
    const { fromdate, todate, user } = searchdata;
    if ((fromdate && todate) || user) {
      let url = "/foodlog/find/0/100/intakeDate?";
      if (fromdate) url = url + "fromDate=" + fromdate;
      if (todate) url = url + "&toDate=" + todate;
      if (user) url = url + "&username=" + user;
      const response = await api.get(url);
      setFoodList(response.data.content);
    } else {
      retrieveFoodList();
    }
  }

  const retrieveFoodList = async () => {
    const response = await api.get("/foodlog/admin/0/100/intakeDate");
    return response.data.content;
  }

  const navigateBackHandler = (url) => {
    navigate(url);
  }

  const navigate = useNavigate();

  const removeFoodHandler = async (id) => {
    await api.delete("/foodlog/delete/" + id);
    const newFoodList = foodlist.filter((food) => {
      return food.id !== id;
    })
    setFoodList(newFoodList);
  }

  useEffect(() => {
    const getAllFoodList = async () => {
      const allfoodList = await retrieveFoodList();
      if (foodlist) setFoodList(allfoodList);
    }
    getAllFoodList();
  }, []);

  useEffect(() => {
    const getAdminData = async () => {
      const response = await api.get("/foodlog/report");
      if (response.data) setAdminData(response.data);
    }
    getAdminData();
  }, []);

  return (
    <div>
      <Header />
      <Routes>
        <Route exact path="/" element={
          <FoodList foodlist={foodlist} getFoodId={removeFoodHandler} searchHandler={searchHandler} />} />
        <Route path="/add" element={<AddFood addFoodhandler={addFoodhandler} navigateBackHandler={navigateBackHandler} />} />
        <Route path="/edit/:id" element={<EditFood updateFoodHandler={updateFoodHandler} navigateBackHandler={navigateBackHandler} />} />
        <Route path="/report" element={<Report admindata={admindata} navigateBackHandler={navigateBackHandler} />} /> 
      </Routes>
      {/* <Header />
      <FoodList foodlist={foodlist} getFoodId={removeFoodHandler} />
      <AddFood addFoodhandler={addFoodhandler} /> */}


    </div>
  );
}

export default App;
