import React from 'react';
import {SafeAreaView, StyleSheet, Button} from 'react-native';

function App() {
  return (
    <SafeAreaView style={styles.container}>
      <Button title="Iniciar Fluxo" />
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
