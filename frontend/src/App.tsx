import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Suspense } from 'react';
import LoginPage from './pages/LoginPage.tsx';
import LoadingSpinner from './components/LoadingSpinner.tsx';
import TabbyFooter from './components/TabbyFooter.tsx';
import MainRouter from './routers/MainRouter.tsx';
import { ErrorBoundary } from 'react-error-boundary';
import GlobalError from './components/GlobalError.tsx';
import NotFound from './components/NotFound.tsx';
import { Provider } from 'react-redux';
import store from './state/store.ts';

export default function App() {
    return (
        <Provider store={store}>
            <div className='mx-auto w-auto'>
                <BrowserRouter>
                    <ErrorBoundary
                        fallbackRender={fallbackProps => <GlobalError fallbackProps={fallbackProps} />}
                        onError={console.log}
                        onReset={() => window.location.reload()}
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
        </Provider>
    );
}
