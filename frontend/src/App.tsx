import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Suspense } from 'react';
import LoginPage from './pages/LoginPage.tsx';
import LoadingSpinner from './components/LoadingSpinner.tsx';
import TabbyFooter from './components/TabbyFooter.tsx';
import MainRouter from './routers/MainRouter.tsx';
import { ErrorBoundary } from 'react-error-boundary';
import GlobalError from './components/GlobalError.tsx';
import NotFound from './pages/NotFound.tsx';
import { useDispatch, useSelector } from 'react-redux';
import { type RootState } from './state/store.ts';
import { setUser } from './state/user/actions/slice/userSlice.ts';
import { type TabbyUser } from './state/user';

interface ExtendedWindow extends Window {
    user?: TabbyUser;
}

export default function App() {
    const { user } = window as ExtendedWindow;
    const userId = useSelector((state: RootState) => state.user.user.id);
    if (user !== undefined && userId === -1) {
        const dispatch = useDispatch();
        dispatch(setUser(user));
    }
    return (
        <div className='mx-auto w-auto'>
            <BrowserRouter>
                <ErrorBoundary
                    fallbackRender={fallbackProps => <GlobalError fallbackProps={fallbackProps} />}
                    onError={console.log}
                    onReset={() => { window.location.reload(); }}
                >
                    <Routes>
                        <Route
                            path={'/'}
                            element={
                                <Suspense fallback={<LoadingSpinner />}>
                                    <MainRouter />
                                </Suspense>
                            }
                        />
                        <Route
                            path={'/auth/login'}
                            element={
                                <Suspense fallback={<LoadingSpinner />}>
                                    <LoginPage />
                                </Suspense>
                            }
                        />
                        <Route path={'*'} element={<NotFound />} />
                    </Routes>
                </ErrorBoundary>
            </BrowserRouter>
            <TabbyFooter />
        </div>
    );
}
