<template>
  <div>

    <div class="title">
      <h1>Package Details</h1>
    </div>
    <div class="float-container">

      <div class="float-child">
        <div>
          <h2>Envelope Size</h2>
        </div>
        <div class="md-layout units">
          <div class="md-layout-item unitField">
            <h3>Unit</h3>
          </div>

          <div class="md-layout-item">
            <div class="radio-field">
              <md-radio v-model="radio" value="mm" class="md-primary">mm</md-radio>
              <md-radio v-model="radio" value="inch" class="md-primary">in</md-radio>
            </div>
          </div>
        </div>

        <div>
          <div class="md-layout inputs">
            <div class="md-layout-item md-size-30 inputFields">
              <md-field>
                <label>Width</label>
                <md-input></md-input>
              </md-field>
            </div>
            <div class="md-layout-item md-size-30 inputFields">
              <md-field>
                <label>Height</label>
                <md-input></md-input>
              </md-field>
            </div>
          </div>
        </div>
      </div>

      <div class="float-child">
        <h2>Envelope Weight</h2>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: "PostalRateCalculator",
  data: () => ({
    radio: false,
    inline: null,
    mm: {name: 'mm'},
    inch: {name: 'in'}
  })
}
</script>

<style scoped>
.title {
  padding-top: 30px;
}

.float-container {
  width: 60%;
  margin: auto;
}

.float-child {
  width: 50%;
  float: left;
  padding: 20px;
}

.md-layout-item {
  border: black;
}

.inputFields {
  margin: auto;
}

.inputs {
  width:70%;
  margin:auto;
}

.units {
  width:90%;
  margin:auto;
}

.unitField {
  width: 20%;
  margin: auto;
}

.radio-field {
  width: 95%;
}

</style>
