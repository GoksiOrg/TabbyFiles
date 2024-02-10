import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import { type TabbyUser, type UserStore } from '../../index';

const initialState: UserStore = {
    user: {
        id: -1,
        username: '',
        roles: [],
        isAdmin: false,
    },
};

const userSlice = createSlice({
    name: 'userStore',
    initialState,
    reducers: {
        setUser(state, action: PayloadAction<TabbyUser>) {
            state.user = action.payload;
        },
        clearUser(state, _: PayloadAction) {
            state.user = initialState.user;
        },
    },
});

export const { setUser, clearUser } = userSlice.actions;
export default userSlice.reducer;
