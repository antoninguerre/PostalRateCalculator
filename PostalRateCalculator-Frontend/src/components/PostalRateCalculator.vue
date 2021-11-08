<template>
  <div>

    <md-dialog-alert
      :md-active.sync="showSizeDialog"
      md-title="Envelope Size Info"
      md-content="Envelope width range: <br> <strong> &nbsp&nbsp&nbsp&nbsp - 90 to 270 mm</strong>, or
      <br> <strong> &nbsp&nbsp&nbsp&nbsp - 3.543 to 10.630 in</strong>
      <br><br>Envelope length range: <br> <strong> &nbsp&nbsp&nbsp&nbsp - 140 to 380 mm</strong>, or
      <br> <strong> &nbsp&nbsp&nbsp&nbsp - 5,512 to 14.961 in</strong>"
      md-confirm-text="Close">
    </md-dialog-alert>

    <md-dialog-alert
      :md-active.sync="showWeightDialog"
      md-title="Envelope Weight Info"
      md-content="Envelope weight range: <br> <strong> &nbsp&nbsp&nbsp&nbsp - 3 to 500 g</strong>, or
      <br> <strong> &nbsp&nbsp&nbsp&nbsp - 0.106 to 17.637 oz</strong>"
      md-confirm-text="Close">
    </md-dialog-alert>

    <div class="title">
      <h1>Envelope Details</h1>
    </div>

    <div class="float-container">

      <div class="float-child">
        <div>
          <h2>Envelope Size</h2>
        </div>
        <div class="md-layout units">
          <div class="md-layout-item">
            <h3>Units</h3>
          </div>

          <div class="md-layout-item md-size-55">
            <div>
              <md-radio v-model="sizeUnit" value="mm" class="md-primary">mm</md-radio>
              <md-radio v-model="sizeUnit" value="in" class="md-primary">in</md-radio>
            </div>
          </div>
        </div>

        <div>
          <div class="md-layout md-gutter inputs">
            <div class="md-layout-item md-size-35 inputFields">
              <md-field>
                <label>Width</label>
                <md-input v-model="width" :disabled="!sizeUnit"></md-input>
              </md-field>
            </div>
            <div class="md-layout-item md-size-35 inputFields">
              <md-field>
                <label>Length</label>
                <md-input v-model="length" :disabled="!sizeUnit"></md-input>
              </md-field>
            </div>
            <div class="infoButton">
              <md-button class="md-icon-button md-size-15" @click="showSizeDialog = true">
                <md-icon>info</md-icon>
              </md-button>
            </div>
          </div>
        </div>
      </div>

      <div class="float-child">

        <h2>Envelope Weight</h2>

        <div class="md-layout units">
          <div class="md-layout-item">
            <h3>Units</h3>
          </div>

          <div class="md-layout-item">
            <div>
              <md-radio v-model="weightUnit" value="g" class="md-primary">g</md-radio>
              <md-radio v-model="weightUnit" value="oz" class="md-primary">oz</md-radio>
            </div>
          </div>
        </div>
        <div class="md-layout weightUnits">
          <div class="md-layout-item md-size-40 inputFields">
            <md-field>
              <label>Weight</label>
              <md-input v-model="weight" :disabled="!weightUnit"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-size-35 infoButton">
            <md-button class="md-icon-button" @click="showWeightDialog = true">
              <md-icon>info</md-icon>
            </md-button>
          </div>
        </div>
      </div>

      <div>
        <md-button class="md-raised md-primary" :disabled="!width || !length || !weight"
                   @click="errorMessageVisibility=false, successMessageVisibility=false,
                    calculatePostalRate(width, length, weight, sizeUnit, weightUnit)">
          Calculate Postal Rate
        </md-button>
      </div>

      <div class="errorMessage md-subheading" v-show="errorMessageVisibility">
        {{ errorMessage }}
      </div>

      <div class="successMessage md-subheading" v-show="successMessageVisibility">
        {{ successMessage }}
      </div>

    </div>

  </div>
</template>

<script>
import {AXIOS} from '../router/index'

export default {
  name: "PostalRateCalculator",
  data: () => ({
    showSizeDialog: false,
    showWeightDialog: false,
    width: '',
    length: '',
    weight: '',
    sizeUnit: '',
    weightUnit: '',
    errorMessage: '',
    errorMessageVisibility: false,
    successMessage: '',
    successMessageVisibility: false,
    millimeters: '',
    inches: '',
    grams: '',
    ounces: ''
  }),
  methods: {
    calculatePostalRate: function (width, length, weight, sizeUnit, weightUnit) {
      AXIOS.post('/postal_rate_calculator', null, {
        params: {
          'width': width,
          'length': length,
          'weight': weight,
          'sizeUnit': sizeUnit,
          'weightUnit': weightUnit
        }
      })
        .then(response => {
          console.log(response.data)

            this.successMessageVisibility = true
            this.successMessage = response.data

        })
        .catch(error => {
          console.log(error.response.data.message)
          this.errorMessageVisibility = true

          if (error.response.data.message.indexOf('width') > -1) {
            this.width = ''
          }

          if (error.response.data.message.indexOf('length') > -1) {
            this.length = ''
          }

          if (error.response.data.message.indexOf('weight') > -1) {
            this.weight = ''
          }

          if (error.response.data.message.indexOf('syntax') > -1) {
            this.errorMessage = error.response.data.message + " Please enter a number."
          }

          if (error.response.data.message.indexOf('range') > -1) {
            this.errorMessage = error.response.data.message
          }

          setTimeout(() => this.errorMessageVisibility = false, 4000)
        })
    }
  }
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

.inputFields {
  margin: auto;
}

.inputs {
  width: 70%;
  margin: auto;
}

.units {
  width: 70%;
  margin: auto;
}

.errorMessage {
  padding-top: 40px;
  color: darkred;
}

.successMessage {
  padding-top: 40px;
  color: black;
}

.infoButton {
  padding-top: 17px;
}

.weightUnits {
  width: 50%;
  margin: auto;
}

</style>
