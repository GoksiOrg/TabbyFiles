import { configureStore } from '@reduxjs/toolkit';
import userReducer from './user/actions/slice/userSlice.ts';

const store = configureStore({
    reducer: {
        user: userReducer,
    },
});

export default store;

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = ReturnType<typeof store.dispatch>;
