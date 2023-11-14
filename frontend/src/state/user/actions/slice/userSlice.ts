import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { TabbyUser, UserStore } from '../../index.ts';

const initialState: UserStore = {
    user: {
        id: -1,
        username: '',
        roles: [],
    },
};

const userSlice = createSlice({
    name: 'userStore',
    initialState: initialState,
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
