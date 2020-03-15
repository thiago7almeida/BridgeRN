import React, {useState, useEffect} from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Button,
  NativeModules,
  NativeEventEmitter,
  Text,
} from 'react-native';

function App() {
  const [token, setToken] = useState('Sem Token');
  useEffect(() => {
    const eventEmitter = new NativeEventEmitter(NativeModules.Wall);
    eventEmitter.addListener('TOKEN', event => {
      setToken(event);
    });
  }, []);
  const callNativeFunction = () => NativeModules?.Wall?.startSDK();
  return (
    <SafeAreaView style={styles.container}>
      <Text>{token}</Text>
      <Button title="Iniciar Fluxo" onPress={callNativeFunction} />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default App;
