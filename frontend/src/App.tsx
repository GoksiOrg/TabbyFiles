import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Suspense } from 'react';
import LoginPage from './pages/LoginPage.tsx';
import LoadingSpinner from './components/LoadingSpinner.tsx';
import TabbyFooter from './components/TabbyFooter.tsx';
import MainPage from './pages/MainPage.tsx';
import { ErrorBoundary } from 'react-error-boundary';
import GlobalError from './components/GlobalError.tsx';
import NotFound from './components/NotFound.tsx';

export default function App() {
    return (
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
                                    <MainPage />
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
