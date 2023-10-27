import { configureStore } from '@reduxjs/toolkit';
import userReducer from './user/actions/slice/userSlice.ts';

const store = configureStore({
    reducer: {
        user: userReducer,
    },
});

export default store;
